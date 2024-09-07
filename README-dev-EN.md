# Developer Notice
## Backend
- After pulling, you need to add the configuration files `application-dev.yml` and `application-prod.yml` by yourself
  ```yml
    web:
      python:
        env: path/to/envs/python.exe # Python.exe in your python environment
        commandTA: path/to/LLIE-Lib/commandTA.py
        input-dir: path/to/LLIE-Lib/inputs # Temporary folder for storing input images
        result-dir: path/to/LLIE-Lib/results # Temporary folder for storing result images
  ```

## Frontend
- After pulling, please execute `npm install` to install the relevant dependencies