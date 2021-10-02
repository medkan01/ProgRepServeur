# ProgRepServeur

To execute this project you will need the other part named [ProgRepClient](https://github.com/medkan01/ProgRepClient).

When lauching the main class, you will need to specify arguments : 
- the name of the host : in our case we will stay in local so `localhost`
- the port on wich you want to run the server

If you are running this on eclipse put this in arguments.
This parameter is accessible as this : 
- right click on the name of the project
- go on `Run As` -> `Run Configurations...`
- go to pannel `Arguments`

If you are running this program on Visual Studio Code, you must have your configuration ready to run Java (install this [extension](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) from vs code ) programs then : 
- open the folder where you have the program
- create a launch.json file where you will put this :
``` json
{
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "Launch Serveur",
            "request": "launch",
            "mainClass": "serveur.Serveur",
            "projectName": "ProgRepServeur",
            "args": ["localhost","the_port_you_want_to_use"]
        }
    ]
}
```
- right click on `Serveur.java` and click `Run Java`
