#!/bin/bash

# Save current directory and move to parent (project root)
CUR_DIR=$(pwd)
cd ..

PROJECT_DIR=$(pwd)
echo "Current directory: $PROJECT_DIR"

# Delete allure-results folder if it exists
if [ -d "allure-results" ]; then
    echo "Deleting existing allure-results folder..."
    rm -rf allure-results
fi

# Check if Allure is installed
if ! command -v allure &> /dev/null; then
    echo "Allure not found. Please install Allure manually."
    cd "$CUR_DIR"
    exit 1
fi

# Build Maven command
MVN_CMD="mvn clean install"
[ -n "$1" ] && MVN_CMD="$MVN_CMD -DbrowserName=$1"
[ -n "$2" ] && MVN_CMD="$MVN_CMD -Denv=$2"
[ -n "$3" ] && MVN_CMD="$MVN_CMD -DThreadCount=$3"

echo "Running: $MVN_CMD"
$MVN_CMD
if [ $? -ne 0 ]; then
    echo "Maven build failed."
    cd "$CUR_DIR"
    exit 1
fi

# Generate Allure report
allure generate allure-results --clean -o allure-report
if [ $? -ne 0 ]; then
    echo "Allure report generation failed."
    cd "$CUR_DIR"
    exit 1
fi

echo "All done!"

# Return to original directory
cd "$CUR_DIR"
