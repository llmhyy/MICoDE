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

You may kindly import the projects in aforementioned repositories first.

## Tool Tutorial
When you run the program successfully, you can see Clonepedia and MICoDE options in Eclipse Preference. Choose the Java project you would like to analyze as follows:

![Snapshot of MICoDe](/screenshot/clonepedia_preference.png?raw=true "Snapshot of Microbat")
![Snapshot of MICoDe](/screenshot/micode_preference.png?raw=true "Snapshot of Microbat")

You need to generate code clone information as follows:
![Snapshot of MICoDe](/screenshot/detect_clone.png?raw=true "Snapshot of Microbat")

Now you can generate templates as follows:
![Snapshot of MICoDe](/screenshot/generate_tempalte.png?raw=true "Snapshot of Microbat")

Afterwards, you need to load generated templates:
![Snapshot of MICoDe](/screenshot/load_template.png?raw=true "Snapshot of Microbat")

You can open the mined templates in the template view:
![Snapshot of MICoDe](/screenshot/show_graph.png?raw=true "Snapshot of Microbat")
