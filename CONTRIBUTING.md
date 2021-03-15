# Contributing

## Contents
* [git](#git)
    + [TLDR](#tldr)
    + [Branches](#branches)
    + [Making a commit](#making-a-commit)
    + [Pushing commits](#pushing-commits)
* [Code Style](#code-style)
  + [Packages](#packages)
  + [Classes and Interfaces](#classes-and-interfaces)
  + [Methods](#methods)
  + [Fields and Attributes](#fields-and-attributes)
  + [Variables and Parameters](#variables-and-parameters)
  + [Acronyms](#acronyms)
  + [Declarations](#declarations)
  + [Classes](#classes)
  + [Spacing](#spacing)
  + [Brace Style](#brace-style)
  + [Conditional Statements](#conditional-statements)
  + [Switch Statements](#switch-statements)

## git
Our development follows the [Gitflow Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).
It is recommended to read and understand this before contributing.

### Branches

- [3.1 Git Branching - Branches in a Nutshell](https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell)
- [Using Branches](https://www.atlassian.com/git/tutorials/using-branches)

`git branch` lists all the branches in the local version of your repository:

```
> git branch
  ai
  audio
  dev
  game-logic
  gui
* master
  networking
  physics
  renderer
```

The current branch is indicated with an `*`.


#### Switching Branches

To switch to a different branch, you can run `git checkout branch-name`. E.g.:

```
> git checkout networking
> git branch 
  ai
  audio
  dev
  game-logic
  gui
  master
* networking
  physics
  renderer
```

The `*` indicates that the current branch is indeed now `networking`.

#### Creating a branch
**Do not** work on the `master` or `dev` branch.

If a feature branch does not exist, create a new branch **off the dev branch**.

E.g.
```
> git checkout dev
> git branch networking
> git checkout networking
```

Create a sub-feature branch **off the feature branch**.

E.g. 
```
> git checkout renderer
> git branch display-shapes
> git checkout display-shapes
```

When a sub-feature branch is complete, it is merged into the feature branch.

When a feature branch is stable (i.e. ready to be merged), it is merged into the `dev` branch.

#### Checkout for Remote Branches
[git checkout a Remote Branch](https://www.git-tower.com/learn/git/faq/checkout-remote-branch/)

List remote branches with `git ls-remote`.

To checkout a remote branch, add the `--track` flag, and the remote branch's ref:
```
> git checkout --track origin/ai
Branch ai set up to track remote branch ai from origin.
Switched to a new branch 'ai'
```

### Making a commit
[git commit](https://www.atlassian.com/git/tutorials/saving-changes/git-commit)

Please use a sensible commit message that tells the rest of the team what change or feature has been added.

E.g. `git commit -m "renderer can now display shapes"`.

Please make commits often. This ensures that the rest of the team can see the steps you've taken to implement
a new feature, and it makes it easier to reset to a previous version of your code.

### Pushing commits
[git push](https://www.atlassian.com/git/tutorials/saving-changes/git-commit)

**Do not push to `master` or `dev`**. Branch off them and merge instead.


## Code Style

### Packages
Each feature should be contained in its own package. 
 
```java
org.nightshade.renderer
org.nightshade.networking
org.nightshade.gui
// etc.
```

### Classes and Interfaces
Written in **UpperCamelCase**. E.g. `ClientController`.

### Methods
Written in **lowerCamelCase**. E.g. `displayCoordinates`.

### Fields and Attributes
Written in **lowerCamelCase**.

Static fields should be written in **uppercase**, with an underscore separating the words:

```java
public static final int THE_ANSWER = 42;
```

### Variables and Parameters
Written in **lowerCamelCase**.

Single character names should be avoided apart from temporary looping variables e.g. `i`.

### Acronyms
In code, acronyms should be treated as words. E.g.

#### Bad
```java
XMLHTTPRequest
String URL
findPostByID
```

#### Good
```java
XmlHttpRequest
String url
findPostById
```

### Declarations
Single declarations per line. E.g.

#### Bad
```java
String username, twitterHandle;
```

#### Good
```java
String username;
String twitterHandle;
```

### Classes
Exactly **one** class per source file, except [inner classes](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html).

### Spacing
Although the university does not teach good spacing practices, it is **VERY** important.

#### Bad
```java
String myName="Dave";
renderer.display(image,400,400);
```

#### Good
```java
String myName = "Dave";
renderer.display(image, 400, 400);
```

This is **not** down to personal preference. Literally every company forces their employees to use good spacing 
practice. 

### Brace Style
Only trailing closing-braces should get their own line. All others appear on the same line as their preceding code.

#### Bad
```java
class MyClass
{
  void doSomething()
  {
    if (someTest)
    {
      // ...
    }
    else
    {
      // ...
    }
  }
}
```

**NEVER** do this.

#### Good
```java
class MyClass {
  void doSomething() {
    if (someTest) {
      // ...
    } else {
      // ...
    }
  }
```

### Conditional Statements

Conditional statements are always required to be enclosed with braces, irrespective of the number of lines required.

`else` statements should be on the same line as the preceding `if` closing-brace.


#### Bad
```java
if (someTest)
    doSomething();
if (someTest) doSomethingElse();

if (someTest) {
    doSomething();
}
else {
    doSomethingElse();    
}
```

#### Good
```java
if (someTest) {
    doSomething();    
}

if (someTest) {
    doSomething();    
} else {
    doSomethingElse();    
}
```

### Switch Statements
Always include the `default` case even if it's just a `break` statement.