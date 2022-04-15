# Encryptor

## Table of contents
* [About](#about)
* [Features](#features)
* [Usage](#usage)
* [Installation](#installation)
* [Technologies used](#technologies-used)
* [Screenshots](#screenshots)


## About

Encryptor is a simple tool which allows password based file encryption/decryption.

## Features

- Encrypt file with one of three available algorithms (shift, aes, blowfish)
- Decrypt file if you know algorithm and password used for encryption

## Usage

This tool can be used through command line arguments from which password and input file name are required.

Available arguments:<br/>
`-m, --mode MODE`                    program mode (default: encryption)<br/>
`-a, --algorithm ALGORITHM`          algorithm to use (default: shift)<br/>
`-p, --password PASSWORD`            password to use with given operation (only numbers for shift)<br/>
`-i, --input-file INPUTFILENAME`     name of the file with input data<br/>
`-o, --output-file OUTPUTFILENAME`   name of the file with output data (default: INPUTFILENAME)

## Installation

1. Import this repository to some folder with `git clone https://github.com/adrian-jrczk/Encryptor.git`
2. Open this folder and install with `mvn clean install`
3. In `target` folder there will be executable jar file `encryptor.jar` which you can move freely and run with `java -jar encryptor.jar`

## Technologies used

- Java 17

## Screenshots

![screenshot 1](images/screenshot01.png?raw=true "Usage example 1")
***
![screenshot 2](images/screenshot02.png?raw=true "Usage example 2")
