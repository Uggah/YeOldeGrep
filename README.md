# YeOldeGrep

## About
This program was built for a optional uni project for the module "Software Development 1" in the course "Medieninformatik", first semester.

This program tries to achieve a similarity to the good old unix grep, but does not try to resemble it completely. Thus, a few adjustments to our needs were made.

We do not provide any support regarding this project. When our project is ready to submit, it probably won't be updated. 

It is NOT and under no circumstances intended to substitute any other utility, it is just for learning's sake.

## How to build and use

### Prerequisites

- maven (version 3.6.3 or newer)
- Java 17 Runtime (Recommended: OpenJDK)

#### Installing the prerequisites

##### Fedora:

```console
[ye@west ~]$ sudo dnf install -y maven java-17
[ye@west ~]$ sudo dnf install maven-openjdk17 --allowerasing
```

##### Debian:

```console
ye@west:~$ sudo apt install -y maven openjdk-17-jre
```

Other debian-based distros could be similar, but that's untested!

### The building procedure

After cloning the repository you just use the following command within the created folder:

`mvn package`

When the process of building finishes, you should find the jar-file in the folder `target`

### How to use
When the JRE is present and installed correctly you should be able to use the following command structure:

`java -jar YeOldeGrep-0.1.jar <args>`

## List of functions
This program incorporates a bunch of functionality seen in grep. Some is pretty much identical, some is adjusted and some is not found in the original at all.

All functions in the following list shall be added to the end of the command seen above.

- `-l <query> <files>`: shows all files in the list of arguments that contain the query.
- `-i <query> <files>`: shows all lines sorted by file in which the query occurs.
- `--help`: shows a general help page, summarizing all functions.

# License
YeOldeGrep (c) 2021 Janno Jens, Henry Russ, Lucca Greschner

SPDX-License-Identifier: GPL-3.0
