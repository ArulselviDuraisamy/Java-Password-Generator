# 🔐 Java GUI Password Generator

A secure and user-friendly **Password Generator** developed using **Java Swing**.

## 📌 Project Description

This project generates random passwords based on the user's selected requirements. The application provides a graphical user interface where users can choose the password length and character types.

The project uses Java's `SecureRandom` class for generating random password characters.

## ✨ Features

* 🔐 Secure password generation
* 📏 Custom password length
* 🔤 Uppercase letters
* 🔡 Lowercase letters
* 🔢 Numbers
* 🔣 Special characters
* 📊 Password strength meter
* 👁️ Show/Hide password
* 📋 Copy password to clipboard
* 🧹 Clear password
* 🖥️ Simple Java Swing GUI

## 🛠️ Technologies Used

* Java
* Java Swing
* AWT
* SecureRandom
* VS Code / IntelliJ IDEA / Eclipse

## ⚙️ How to Run

### Step 1: Compile

```bash
javac PasswordGeneratorGUI.java
```

### Step 2: Run

```bash
java PasswordGeneratorGUI
```

## 🔄 Working

```text
User enters password length
          ↓
Selects character options
          ↓
Clicks Generate Password
          ↓
SecureRandom generates password
          ↓
Password strength is calculated
          ↓
Password is displayed
          ↓
User can Show / Copy / Clear
```

## 📊 Password Strength

The application evaluates the password based on:

* Password length
* Uppercase characters
* Lowercase characters
* Numbers
* Special characters

The password is classified as:

* Weak
* Medium
* Strong

## 📁 Project Structure

```text
Java-Password-Generator
│
├── PasswordGeneratorGUI.java
├── README.md
└── screenshot.png
```

## 🎯 Objective

The main objective of this project is to develop a simple GUI application that can generate random and relatively strong passwords while allowing users to customize the password according to their requirements.

## 👩‍💻 Author

Developed as a Java project.
