@echo off
REM Windows batch script to start the Playwright+Cucumber+TestNG framework

REM Check if Allure is installed
where allure >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo Allure is not installed. Installing Allure CLI using Scoop...
    powershell -Command "Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; if (-not (Get-Command scoop -ErrorAction SilentlyContinue)) { iwr -useb get.scoop.sh | iex }; scoop install allure"
    echo Please restart your terminal or ensure Allure is in your PATH before running tests.
    exit /b 1
) else (
    echo Allure is already installed.
)


