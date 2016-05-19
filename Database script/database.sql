SELECT * FROM dominioncard;

DROP DATABASE dominioncard;


CREATE DATABASE dominioncard;
USE dominioncard;

CREATE TABLE cards( 
ID INT NOT NULL UNIQUE, 
NAME VARCHAR(100) NOT NULL UNIQUE, 
Cost INT NOT NULL,
Image VARCHAR(100) NOT NULL, 
TYPE INT NOT NULL,
Worth INT,
VictoryPoints INT,
StartAmount INT NOT NULL,
PRIMARY KEY(ID) 
);


/*firstGame*/
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(1 ,"cellar", 2, "C:\\School\\DominionFotos\\cellar.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(2 ,"market", 5, "C:\\School\\DominionFotos\\market.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(3 ,"militia", 4, "C:\\School\\DominionFotos\\militia.jpg",1,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(4 ,"mine", 5, "C:\\School\\DominionFotos\\mine.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(5 ,"moat", 2, "C:\\School\\DominionFotos\\moat.jpg",4,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(6 ,"remodel", 4, "C:\\School\\DominionFotos\\remodel.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(7 ,"smithy", 4, "C:\\School\\DominionFotos\\smithy.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(8 ,"village", 3, "C:\\School\\DominionFotos\\village.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(9 ,"woodcutter", 3, "C:\\School\\DominionFotos\\woodcutter.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(10,"workshop", 3, "C:\\School\\DominionFotos\\workshop.jpg",0,NULL,NULL,10);

/*bigMoney*/
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(11 ,"adventurer", 6, "C:\\School\\DominionFotos\\adventurer.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(12 ,"bureaucrat", 4, "C:\\School\\DominionFotos\\bureaucrat.jpg",1,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(13 ,"chancellor", 3, "C:\\School\\DominionFotos\\chancellor.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(14 ,"chapel", 2, "C:\\School\\DominionFotos\\chapel.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(15 ,"feast", 4, "C:\\School\\DominionFotos\\feast.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(16 ,"laboratory", 5, "C:\\School\\DominionFotos\\laboratory.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(17 ,"moneylender", 4, "C:\\School\\DominionFotos\\moneylender.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(18 ,"throne room", 4, "C:\\School\\DominionFotos\\throneroom.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(31 ,"council room", 5, "C:\\School\\DominionFotos\\councilroom.jpg",0,NULL,NULL,10);

/*interaction*/
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(19 ,"festival", 5, "C:\\School\\DominionFotos\\festival.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(20 ,"library", 5, "C:\\School\\DominionFotos\\library.jpg",0,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(21 ,"spy", 4, "C:\\School\\DominionFotos\\spy.jpg",1,NULL,NULL,10);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(22 ,"thief", 4, "C:\\School\\DominionFotos\\thief.jpg",1,NULL,NULL,10);

/*sizeDistortion*/
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(23 ,"gardens", 4, "C:\\School\\DominionFotos\\gardens.jpg",3,NULL,NULL,12);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(24 ,"witch", 5, "C:\\School\\DominionFotos\\witch.jpg",1,NULL,NULL,10);

/*treasurecards*/
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(25 ,"copper", 0, "C:\\School\\DominionFotos\\copper.jpg",2,1,NULL,60);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(26 ,"silver", 3, "C:\\School\\DominionFotos\\silver.jpg",2,2,NULL,40);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(27 ,"gold", 6, "C:\\School\\DominionFotos\\gold.jpg",2,3,NULL,30);

/*victorycards*/
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(28 ,"estate", 2, "C:\\School\\DominionFotos\\estate.jpg",3,NULL,1,24);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(29 ,"duchy", 5, "C:\\School\\DominionFotos\\duchy.jpg",3,NULL,3,12);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(30 ,"province", 8, "C:\\School\\DominionFotos\\province.jpg",3,NULL,6,12);
INSERT INTO cards(ID, NAME, Cost, Image,TYPE,Worth,VictoryPoints,StartAmount) VALUES(32 ,"curse",0,"C:\\School\\DominionFotos\\curse.jpg",3,NULL,-1,30);



