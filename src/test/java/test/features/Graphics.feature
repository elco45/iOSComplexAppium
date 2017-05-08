Feature: Verify something appears after clicking any option in the main page

Scenario Outline: There are elements
Given The list of elements in main page
When Click on "<arg1>" button
Then I can view a "<arg2>" element


Examples:
|   arg1           |   arg2         |
|   Action Sheets  |   Okay         |
|   Text View      |   Text View    |