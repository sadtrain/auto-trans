set "projectPath=C:\Users\Administrator\IdeaProjects\auto-trans"
set "backupPath=C:\opt\auto_trans\backup"
set "jarPath=C:\opt\auto_trans"
set "javaProcessName=zgs-mirai"
set "errorlevel="
set "jarname=auto_trans"
chcp 65001 > nul
set "JAVA_HOME=C:\Users\Administrator\.jdks\corretto-11.0.19"
set "PATH=%JAVA_HOME%\bin;%PATH%"
REM 停止正在运行的Java进程
taskkill /F /IM "%javaProcessName%"

REM 获取当前日期和时间作为后缀
for /F "usebackq tokens=1-7 delims=/:. " %%a in ('%DATE% %TIME%') do (
  set "suffix=%%a%%b%%c%%d%%e%%f%%g"
)

REM 备份JAR文件并删除旧的备份
for /F "skip=5 delims=" %%F in ('dir /B /O-D "%backupPath%\%jarname%.jar*"') do (
  del "%backupPath%\%%F"
)

copy "%jarPath%\%jarname%.jar" "%backupPath%\%jarname%-%suffix%.jar"

del "%jarPath%\%jarname%.jar"
REM 进入项目目录，拉取最新代码并进行打包
cd "%projectPath%"

git config http.proxy socks5://127.0.0.1:10808
git config https.proxy socks5://127.0.0.1:10808
git pull
call mvn clean package -DskipTests

REM 将打包生成的JAR文件复制到指定路径
copy "%projectPath%\target\%jarname%.jar" "%jarPath%\%jarname%.jar"

REM 启动Java进程
java -jar "%jarPath%\%jarname%.jar"


  pause
