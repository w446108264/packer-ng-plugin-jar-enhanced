# packer-ng-plugin-jar-enhanced

> j.s 🇨🇳
 
Fork https://github.com/mcxiaoke/packer-ng-plugin

支持更多命令

```shell
                           .-'      '-. 
                          /            \ 
                         |              | 
                         |,  .-.  .-.  ,| 
                         | )(__/  \__)( | 
                         |/     /\     \| 
            (@_          (_     ^^     _) 
 _           ) \__________\__|IIIIII|__/_____________________________ 
(_)Zhongdaxia{}<___________|-\IIIIII/-|______________________________> 
             )_/           \          / 
            (@              `--------`


Packer-ng-plugin-jar
Using the command to increased channel information for Apk
Base on https://github.com/mcxiaoke/packer-ng-plugin
Enhanced version https://github.com/w446108264/packer-ng-plugin-jar
Copyright 2016 Sj <shengjun8486@gmail.com>

Usage: java -jar PackerNg-x.x.x.jar --help
Usage: java -jar PackerNg-x.x.x.jar apkFile [outputDir] --m marketFile
Usage: java -jar PackerNg-x.x.x.jar apkFile [outputDir] --c channelname1 channelname2 ...

 [options] FilePaths include apkFile(can't be null) 
           and outputDir(The same path with jar when null)
  Options:
    -c, --c, --channels
      for multiple channels like --channels a1 a2 a3
      Default: []
    --help, -help, -?, -h
      Get help for the particular command.
      Default: false
    -m, --m, --marketFile
      for multiple channels form marketFile.txt
      (These are to be construed as commentary which start with #)
      
```shell