@echo off
setlocal

REM Save current directory and move to parent
set "CUR_DIR=%cd%"
cd ..
set "PROJECT_DIR=%cd%"
echo Current directory: %PROJECT_DIR%

REM Delete allure-results folder if it exists
if exist "allure-results" (
    echo Deleting existing allure-results folder...
    rmdir /s /q "allure-results"
)

REM Check if Allure is installed
where allure >nul 2>nul
if %errorlevel% neq 0 (
    echo Allure not found. Please install Allure manually.
    cd "%CUR_DIR%"
    exit /b 1
)

REM Build Maven command
set "MVN_CMD=mvn clean install"
if not "%1"=="" set "MVN_CMD=%MVN_CMD% -DbrowserName=%1"
if not "%2"=="" set "MVN_CMD=%MVN_CMD% -Denv=%2"
if not "%3"=="" set "MVN_CMD=%MVN_CMD% -DThreadCount=%3"

echo Running: %MVN_CMD%
%MVN_CMD%
set "MVN_EXIT_CODE=%errorlevel%"

allure serve allure-results

echo All done!

REM Return to original directory
cd "%CUR_DIR%"
endlocal
exit /b %MVN_EXIT_CODE%
