#!/bin/bash
# Shell script to start the Playwright+Cucumber+TestNG framework on Mac/Linux

# Check if Allure is installed
if ! command -v allure &> /dev/null
then
    echo "Allure is not installed. Installing Allure CLI using Homebrew..."
    if ! command -v brew &> /dev/null
    then
        echo "Homebrew is not installed. Please install Homebrew first: https://brew.sh/"
        exit 1
    fi
    brew install allure
    echo "Please restart your terminal or ensure Allure is in your PATH before running tests."
    exit 1
else
    echo "Allure is already installed."
fi



