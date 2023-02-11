# Java Christmas Project

The purpose of the project is to create a simulation of Santa Claus delivering gifts to children on Christmas Eve. This project will demonstrate the use of object-oriented programming concepts such as design-patterns, inheritance, polymorphism, and encapsulation.<br>

# Implementation

I have attempted to modularize the code as best as possible using the Command Pattern, Factory Pattern, Strategy Pattern, Singleton, and abstract class-based implementations. The comments placed in the code also explain certain functionalities quite well.<br>

For the Command Pattern, I created a separate package that contains multiple packages that contain different commands.<br>

For the classes that calculate the score for each child, I created an abstract class called Score, which is extended by the categories of children (Baby, Kid, Teen, Adult). Additionally, to calculate the score, we need to determine which class the child belongs to, so I used the ScoreFactory for this functionality. In the main method, I called the CalculateScore function in each round for each child. This returns the averageScore. For calculating the score for each city, I wrote a separate function that returns a descendingly ordered hashmap based on the score that I use in the NiceCityGiftStrategy to first distribute gifts to children based on the city and then based on the ID.<br>

For the budget, I built two functions:<br>
- CalculateScoresSum -> Sums up all the averageScores of the children and sets the niceScore for each child by calling the CalculateScore function on the entire list of children.<br>
- CalculateChildBudget -> Calculates Santa's budget for each child (also called in each round, for each child). The CalculateScoreSum calls CalculateScore within it and modifies the "niceScore" parameter of each child at the beginning of each round.<br>

I implemented elves using a factory. Elf modifications (except for yellow) are applied after calculating the budget for each child. To apply the modifications of the Yellow elf, after gift distribution, I check if the child received any gifts. If not, I apply the modifications of the elf.<br>

Distribution takes place in SendChildListGifts, which is called by each gift distribution strategy in the order of the list given as a parameter, the order of which I modify based on the strategy. <br>
Example: ID strategy -> I sort the list of children based on ID, then call the distribution function on the sorted list.<br>
Each gift distribution strategy returns a JSONArray that contains the children who received gifts in that round.<br>