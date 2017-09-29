# MICoDE

MICoDE is a Eclipse plugin for summarizing recurring design in code repository into template for code generation. 

![Snapshot of MICoDe](/image/micode_screenshot.jpg?raw=true "Snapshot of Microbat")

MICoDE is short for "Mining Implicit Code Design", which is a tool to automatically extract and abstract recurring designs from code base into design templates. The mined templates allows developers to make further customization for generating new code. The generated code involves the code skeleton of recurring design as well as the code bodies annotated with comments and hints to remind developers of necessary modification. A short demonstration of MICoDe is available in http://linyun.info/micode/index.html.

## Set Up Source Code
MICoDE requires install GMF (Graphic Modelling Framework) plugin for generating graphic UML-like model. You may install the plugin through Eclipse Market in your Eclipse.

Moreover, this plugin depends on three projects:
1. mcidiff (https://github.com/llmhyy/mcidiff)
2. clonepedia (https://github.com/llmhyy/clonepedia)
3. datamining (https://github.com/llmhyy/data_mining)

You may kindly important the projects in aforementioned repository first.



