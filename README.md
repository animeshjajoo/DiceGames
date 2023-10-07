[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/yBUeQ8Fo)
# A3-Dice Games

Name of the project: Dice Games

Animesh Jajoo(2020B3A71260G)-f20201260@goa.bits-pilani.ac.in </br>
Bharath Kalyan B (2020B4A71354)-f20201354@goa.bits-pilani.ac.in 

This app is an extension of the previous assignment A2. </br>
This app simulates a die game. For the first game, with a single die, your score/coins increase by 5 every time a 6 is rolled. For the second game containing four dies rolled at once, you can bet a certain number of coins on getting "2 alike," "3 alike," or "4 alike" die rolls. If you win, you earn double, triple, or quadruple the coins you bet. If you lose, you lose the coins you bet. Your balance is updated based on the outcome, and the wager input is reset.

Our app has no obvious bugs at the time of writing this README.

### Approach to solving:
### UI Layouts

- The app uses separate XML layouts for portrait and landscape orientations for different screens/game states.
  (`activity_wallet.xml`, `activity_two_or_more.xml`, `information_of_dice_games.xml`)

### ViewModel

- The ViewModels (`WalletViewModel.java`, `TwoOrMoreActivityViewModel.java`) are used to manage the game state and ensure UI state persistence.
- It includes methods for rolling the dice, choice of type of game (2 alike, 3 alike, 4 alike), selection of wager amount, and tracking game statistics.

### Toast Feedback

- A toast message, shown when the player earns coins, is implemented in the `WalletActivity.java` class using Toast.maketext()
- The toast message displays "Success!" when coins are earned.
  
- A toast message, shown when the player earns or loses coins, is implemented in the `TwoOrMoreActivity.java` class using Toast.maketext()
- The toast message displays "Success!" when coins are earned, "Loss!" when coins are lost, "Select a Game Type" when no game type is selected, "Enter a Wager" when no wager is entered and "Wager is Invalid" when an invalid wager is entered.

Testing: We initially began by writing the code without employing a test-driven approach. Instead, we opted for a user-testing approach, testing the app with some of our friends to identify bugs and collect feedback. We developed our logic using a basic UI, tested the app, addressed the identified bugs, and iteratively repeated this process until our app functioned flawlessly across all conditions. 

### Accessibility
-To ensure accessibility in our Android app, we utilized Espresso for testing. We've added 7 instrumented test cases.

Pair Programming: 4/5 </br>
Assignment Difficulty: 8/10 </br>

Credits: We read StackOverflow and the slides on the course website and took inspiration from it (sometimes copy-pasting code from it). A huge thank you to our hostel mates for testing our app. We also received valuable information from the Open-Source Android Studio documentation.
