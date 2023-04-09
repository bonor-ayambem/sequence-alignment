
# Annotating Viral Genomes

## Purpose

This project is an introduction to the way biological systems organize data within
genome sequences, and to some of the tools developed to decipher this organization.
This will be done by finding and comparing genes within viral genomes

## Project Description

Two nucleotide sequences are provided in a fasta file.
After this program is run, those sequences should contain gaps in them, with the 
goal of aligning the nucleotides in both sequences. This is also done with the goal
of minimizing the number of gaps in each sequence.

## Instructions

- This program is implemented using `java`. As a result, it will require a
Java Runtime Environment (JRE) in order to run
- Create the fasta file that contains the reads you want to align and
save it in the source folder
- In your JRE, compile the program using `javac main.java`
- Run the program using `java main`
- Enter the name of the fasta file you provided to be sequenced

## Test Run

Fasta file called test.fasta is provided as follows:
```
   > sequence 1
    CAGTA
    > sequence 2
    CAT
```

Run the following lines of code:

 `javac main.java`
 `java main`
 
```
    Enter File Name:
    test.fasta
```


The output is printed out as follows:
```
    > sequence 1
    CAGTA
    > sequence 2
    CAT--
```