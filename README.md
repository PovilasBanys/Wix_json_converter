# Wix_json_converter

1. Application downloads sheet with data from URL: https://www.wix.com/_serverless/hiring-task-spreadsheet-evaluator/sheets
2. Stores sheet into json file (info.json)
3. Code applies specific rules (https://github.com/wix-incubator/wix-grow/blob/main/Technical%20task/Task.pdf) as described in the task
4. Changed json file is saved in result file (final.json)
5. File is sent to server (https://www.wix.com/_serverless/hiring-task-spreadsheet-evaluator/verify/eyJ0YWdzIjpbXX0) where it evaluates if result file is corectly changed
6. Application tracks server responses or errors
