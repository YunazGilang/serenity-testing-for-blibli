Meta: user is attempting to search for a product on blibli.com

Narrative:
As a user
I want to perform an action to search a product in the website
So that I can know the information of the product that i searched in the website

GivenStories: stories/profile/UpdateStoryTest.story

Scenario: user is searching a product and then hit enter when the popup still block the search button
Meta:
Given The popup is still exist user choose to 'close'
When The user is searching for 'Asus' and then the user do 'enter'
Then The user should see the search result of 'Asus'

Scenario: user is searching invalid product and then hit enter when the popup still block the search button
Meta: @skip
Given The popup is still exist user choose to 'close'
When The user is searching for '<style>body{display:block !important;}</style>' and then the user do 'enter'
Then The user should see the search result of ' '