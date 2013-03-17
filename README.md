#IK-Analyzer

IK Analyzer 是一个开源的，基于 java 语言开发的轻量级的中文分词工具包。从 2006 年 12 月推出 1.0 版开始，IKAnalyzer 已经推出了 4 个大版本。最初，它是以开源项目 Luence 为应用主体的，结合词典分词和文法分析算法的中文分词组件。从 3.0 版本开始，IK 发展为面向 Java 的公用分词组件，独立于 Lucene 项目，同时提供了对 Lucene 的默认 优化实现。在 2012 版本中，IK 实现了简单的分词歧义排除算法，标志着 IK 分词器从单纯 的词典分词向模拟语义分词衍化。

## 关于本项目
IK-Analyzer 的作者为林良益（<linliangyi2007@gmail.com>），项目网站为 <http://code.google.com/p/ik-analyzer/>。

本项目是对 IK-Analyzer 的 maven 包装。目前包含 IK 的两个版本，分别是:

* **Master** 分支：是由王坤山创建（<wks1986@gmail.com>）。包含 IK 版本 3.x。项目地址 <https://github.com/wks/ik-analyzer> 
* **2012FF** 分支：包含 IK 版本 v2012-FF，适用于 Lucene 4.0+。<https://github.com/Leegorous/ik-analyzer/tree/2012FF> ，在 clone 之后需要 `git checkout 2012FF`。这个分支目前标记为 SNAPSHOT ，需要做 release 的同学请自行修改版本号。


## IK-Analyzer v2012 特性:

* 采用了特有的“正向迭代最细粒度切分算法“，支持细粒度和智能分词两种切分模式。* 在系统环境: Core2 i7 3.4G 双核，4G 内存，window 7 64 位， Sun JDK 1.6_29 64位 普通 pc 环境测试，IK2012 具有 160 万字/秒(3000KB/S)的高速处理能力。* 2012 版本的智能分词模式支持简单的分词排歧义处理和数量词合并输出。* 采用了多子处理器分析模式，支持: 英文字母、数字、中文词汇等分词处理，兼容韩文、日文字符* 优化的词典存储，更小的内存占用。支持用户词典扩展定义。特别的，在2012版本，词典支持中文，英文，数字混合词语。

## Maven用法：

将以下依赖加入工程的 pom.xml 中的 `<dependencies>...</dependencies>` 部分。

    <dependency>
        <groupId>org.wltea.ik-analyzer</groupId>
        <artifactId>ik-analyzer</artifactId>
        <version>20130317-SNAPSHOT</version>
	</dependency>

在IK Analyzer 加入 Maven Central Repository 之前，你需要手动安装，安装到本地的repository，或者上传到自己的Maven repository服务器上。

要安装到本地 Maven repository，使用如下命令，将自动编译，打包并安装：
mvn install -Dmaven.test.skip=true

