# 开发者须知
## 后端
- 拉取后需自行添加配置文件`application-dev.yml`和`application-prod.yml`
  ```yml
    web:
      python:
        env: path/to/envs/python.exe # python环境的python.exe
        commandTA: path/to/LLIE-Lib/commandTA.py
        input-dir: path/to/LLIE-Lib/inputs # 暂时存放待处理图片的文件夹
        result-dir: path/to/LLIE-Lib/results # 暂时存放已处理图片的文件夹
  ```

## 前端
- 拉取后需要执行`npm install`安装相关依赖