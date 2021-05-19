# Production Planning & Control

In order to learn some Java, I will build this PCP application. Right now I'm focusing on the business logic (forecasting, MRP and PERT/CPM), and after that I will make it useful. Here is the roadmap: 

### FORECASTING

Quantitative methods

:white_check_mark: Simple moving average

:white_check_mark: Weighted moving averages

:white_check_mark: Simple exponential smoothing

:white_check_mark: Regression

Forecast errors

:white_check_mark: Mean Forecast Error

:white_check_mark: Mean Absolute Deviation

:white_check_mark: Tracking Signal

### MRP (Material Requirement Planning)

:white_check_mark: Transverse the tree

:white_check_mark: Calculate the order for all children of a item

:white_large_square: Calculate the order for all children of a item, unifying repeated items in a single production order

:white_check_mark: Add children after the creation of the item, set/update children and delete children

:white_check_mark: Generate Bill of Materials (BOM)


### PERT/CPM
:white_large_square: PERT/CPM

### MAKING IT USABLE
:white_large_square: Build the project as a Spring API

:white_large_square: Add user login (OAuth maybe?)

:white_large_square: Develop a database (SQL or NoSQL, don't know yet)

:white_large_square: Develop a web user interface (maybe pure Javascript, maybe React, don't know yet)

:white_large_square: Develop a desktop user interface (maybe Electron, maybe JavaFX, don't know yet)

### NOT ANYTIME SOON
:white_large_square: Generate PDF reports with tables, graphs, all the pretty stuff

:white_large_square: Send reports to a email list

### PREVIEW

FORECASTING

You can do linear regression with the Linear Least Squares method, like that:

Create the data.
```
Data x = new Data(1, 2, 3, 4, 5, 6, 7, 8);
Data y = new Data(256, 312, 426, 278, 298, 387, 517, 349);
```

Put the data in the constructor of LinearLeastSquares.
```
LinearLeastSquares lls = new LinearLeastSquares(x, y);
```

Print the results.
```
lls.print();
y = 18,80x + 268,29
```

Given a demand and its forecast, you can calculate some forecast errors.

Initialize the demand and forecast data.
```
Data d = new Data(12, 15, 13, 16, 14, 12);
Data f = new Data(14, 13, 12, 13, 15, 14);
```

Instantiate  a new error, with the datasets.
```
Error e = new Error(d, f);
```

Calculate the Mean Forecast Error, Mean Absolute Deviation and Tracking Signal errors.
```
e.mfe();
e.mad();
e.trackingSignal();
```

MRP

Given a tree of materials, and the requirements for the root item, you can generate the order production for all chidren.

<img src="img/tree.png" width="40%"/>

Here is the constructor of a item and it's children:
```
public Item(String name, double initialStock, double leadTime, double safetyStock, double lotSize, Child... children);
public Child(Item item, int quantity);
```

You create the items.
```
Item c = new Item("C", 100, 1, 50, 600);
Item d = new Item("D", 100, 2 , 100, 400);
Item b = new Item("B", 50, 2, 0, 80);
Item a = new Item("A", 50,  1, 0, 200, new Child(c, 4), new Child(d, 3));
Item y = new Item("Y", 120, 1, 0, 140, new Child(a, 2), new Child(b, 1));
```

Set the demand for the root item.
```
y.demand(0, 0, 0, 120, 0, 50, 80, 90, 0, 180, 80, 90);
```

Calculate the planned order release. The argument in plan() is the multiplicative of the item for a given tree. For the root item, it will always be 1. In the case of item A, for this tree, it will be 2, and for C, it will be 4.
```
MRP mrp = y.plan(1);
```

And print the results.
```
mrp.print();
```
<img src="img/mrp.png" width="80%"/>
