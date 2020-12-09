# Jmtrace
A simple tool for java memory tracing by using instrumentation.
## Environment
tested on Ubuntu 20.04
- maven 3.6.3
- java 1.8
## Build & Run
- You can use `./jmtrace` to not only build the source files with maven but also test `.jar` files like below:  
  `./jmtrace -jar [path/to/your/test/jar/files]`

- Some testcases have been provided for you in the folder `testcase/`

- Except for outputs of the original probgram, every line of the outputs consists of four parts like below:

  `[R/W] [threadID] [ObjectIndentity] [Object]`

