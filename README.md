# SCXML to Java code builder

This project is a code generator for state machines written using the [SCXML specification](https://www.w3.org/TR/scxml/).
The generated code is in Java.
It was designed as a school project.

## Features

- Support of simple state machines (non hierarchical, non parallels).
- Support of event priority.
- Support of temporal events (wait before sending an event).
- Support of arbitrary code call. States and transitions can call any user defined methods.

## Building the generator

```bash
mv clean compile assembly:single
```

## Running tests

```bash
mvn clean test
```

## Running the generator

```bash
java -jar scxml-to-fsm...jar REPOSITORY SCXML_FILE
```

All paths must be ABSOLUTE.

More information on how to use the generator in the PDF file `User Guide V1.pdf` (French only).
