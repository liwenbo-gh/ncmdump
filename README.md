### JAVA版转换网易云ncm文件的项目

#### 感谢

- [NinoDEVLOP/ncmdump](https://github.com/NinoDEVLOP/ncmdump)：我作为参考的Java项目

- [nondanee/ncmdump](https://github.com/nondanee/ncmdump)：NinoDEVLOP参考的python项目

- [anonymous5l/ncmdump](https://github.com/anonymous5l/ncmdump)：python项目参考的原始C++项目

#### 使用说明

​    在命令行中使用```java -Dfile.encoding=utf-8 -jar <jarFile> <filePath>```,示例:

```
java -Dfile.encoding=utf-8 -jar ncmdump-jar-with-dependencies.jar C:\music
#当然也可以直接转换ncm文件
java -Dfile.encoding=utf-8 -jar ncmdump-jar-with-dependencies.jar C:\music\test.ncm

```

​    正常格式的音乐文件报存在./music件夹中。
