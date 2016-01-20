CREATE TABLE Todos(
    _ID INTEGER,
    Todo NVARCHAR(150),
    CreatedAt DATETIME
);


INSERT INTO Todos
VALUES(1, 'Call Mom', '01-20-2016');

SELECT * FROM Todos;


DROP TABLE Customers;


--Constraints: In table
CREATE TABLE Persons
(
PersonID int --PRIMARY KEY,
LastName varchar(255),
FirstName varchar(255),
Address varchar(255),
City varchar(255),

CONSTRAINT PK_Persons PRIMARY KEY(PersonID)
);

--After table creation we add the constraints.
ALTER TABLE Persons
ADD CONSTRAINT PK_Persons PRIMARY KEY(PersonID);



--Unique Constraints
CREATE TABLE Students(
    ID INT NOT NULL PRIMARY KEY,
    Name VARCHAR(50),
    NationalID VARCHAR(20),
    CONSTRAINT UQ_Student_NationalID UNIQUE(NationalID)
);


INSERT INTO Students(ID, name, nationalid)
VALUES (1, 'abc', 'cde')

--This will fail - Not Unique
INSERT INTO Students(ID, name, nationalid)
VALUES (1, 'moshe', 'cde')


--Default Constraints:
CREATE Table MyOrders(
    Orderid INTEGER,
    CustomerID INTEGER,
    EmployeeID INTEGER,
    ProductID INTEGER,
    Quantity INTEGER,
    UnitPrice INTEGER,
    OrderDate DATETIME-- DEFAULT GETDATE(),
    CONSTRAINT DF_OrderDate DEFAULT '01-20-2015' FOR(OrderDate)
)


--Check Constraints:
CREATE TABLE Grades(
    GradeID INTEGER NOT NULL,
    Grade INTEGER CHECK (Grade > 0)
)

--This will Fail:
INSERT INTO Grades
VALUES(2, -20);

CREATE TABLE Grades(
    GradeID INTEGER NOT NULL,
    Grade INTEGER CHECK (Grade > 0),
    StudentID INT,
    CONSTRAINT fk_GradesStudents FOREIGN KEY (StudentID)
    REFERENCES Persons(ID);
)

--
ALTER TABLE Grades
ADD CONSTRAINT FK_GradesStudents FOREIGN KEY (StudentID)
REFERENCES Persons(ID) ON DELETE CASCADE;



CREATE TABLE Employees(
             _ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
             FirstName TEXT NOT NULL,
             LastName TEXT NOT NULL,
             Address TEXT,
             City TEXT,
             Country TEXT,
             Phone TEXT,
             Email TEXT);



 CREATE TABLE Orders(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                        ProductID INTEGER NOT NULL,
                        Quantity INTEGER NOT NULL,
                        EmployeeID INTEGER NOT NULL,
                        CustomerID INTEGER NOT NULL,
                        FOREIGN KEY ProductID REFERENCES Products(_ID),
                        FOREIGN KEY EmployeeID REFERENCES Employees(_ID),
                        FOREIGN KEY CustomerID REFERENCES Customers(_ID),
 );


