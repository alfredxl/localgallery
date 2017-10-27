localgallery<br>
==
本项目完全基于:https://github.com/YancyYe/GalleryPick<br>

使用说明:
--
a、导入本插件：
>在项目目录的gradle文件中添加如下地址:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
>在Module的gradle文件夹中加入:
```groovy
dependencies {
    ...
    compile 'com.github.alfredxl:localgallery:v1.0.0'
}
```