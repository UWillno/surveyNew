# 学院调查管理系统、问卷管理系统

## 扯淡

这个是当时实习实训课程的源码和笔记，因为我自己的源码忘记提交丢失了，当时，群也退了，所以之前那个仓库最后就关了。最近清Onedrive又找到了当时群里老师发的文件还有视频，都自动备份了，有空传上来，方便学习、前端差几个页面，自己加一下问题不大，都是一个样式，后端几乎不用写，我就加了个跨域配置，因为我发现前端请求不了。懒得配置环境不保证没别的问题，看视频学应该问题不大……

## 搭建、启动

数据库执行SQL创建、修改下后端的配置src\main\resources\application.properties文件，确保能连通数据库

前端node.js版本高了可能跑不起来，用这个试试

```
set NODE_OPTIONS=--openssl-legacy-provider
npm run serve
```

不行就降级或者别的方法…

后端建议放IDEA里，当然如果你配置了环境，可以直接

```
mvn spring-boot:run
```

## 原视频教学、原压缩包

仓库里的是我解压过，有细微改动的，跑不通就用原版试试或者直接按笔记重新生成。

[OneDrive](https://1drv.ms/f/c/28e50e4b3817399f/ElkYtrDeJXFBiJn4OWixziYBur26Yx7iJZyhqfhCRz-lPg?e=fVyfZ6)

## 跑通视频

[bilibili](https://www.bilibili.com/video/BV1psytYiESo/)
