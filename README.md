# Encryptor

## Table of contents
* [About](#about)
* [Features](#features)
* [Usage](#usage)
* [Installation](#installation)
* [Technologies used](#technnologies-used)
* [Screenshots](#screenshots)


## About

Encryptor is a simple tool which allows password based file encryption/decryption.

## Features

- encrypt file with one of three available algorithms (shift, aes, blowfish)
- decrypt file if you know algorithm and password used for encryption

## Usage

This tool can be used through CLI arguments from which password and input file name are required.

Available arguments:
`-m, --mode MODE`						program mode (default: encryption)
`-a, --algorithm ALGORITHM`				algorithm to use (default: shift)
`-p, --password PASSWORD`				password to use with given operation (only numbers for shift)
`-i, --input-file INPUTFILENAME`		name of the file with input data
`-o, --output-file OUTPUTFILENAME`		name of the file with output data (default: INPUTFILENAME)

## Installation

1. Import this repository to some folder with `git clone repourl`
2. Open this folder and install with `mvn clean install`
3. In `target` folder there will be executable jar file `encryptor.jar` which you can move freely and run with `java -jar encryptor.jar`

## Technologies used

- Java 17

## Screenshots

![screenshot 1](images/screenshot01.png?raw=true "Usage example 1")
![screenshot 2](images/screenshot02.png?raw=true "Usage example 2")