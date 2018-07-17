# SafeWalk Project

## FEATURES LIST:

## Home Page (logged out):

- Anyone can visit this page, whether they are logged in or not. Visitors can see crime events without specific classification. 
- All features available to logged in users are listed to the left, along with buttons to either login or sign up.


## Login Page:

- Logged out users can log in.
- If a user is already logged in and lands on this page, they will be redirected to the **“logged in”** home page.


## Register Page:

- Users without accounts can create a new account, and will be directed to the login page after an account is created.
- If a user is already logged in and lands on this page, they will be redirected to the **“logged in”** home page.


## Logged In Home Page:

Users can interact with the map in the following ways
- See information based on their current location
- Turn map layers on and off
- Crime events *(with color coordinated pins)*
- Public safety locations *(police/fire stations)*
- **“Heatmap”** layer based on a custom algorithm
- Bars/restaurants
- **“Family friendly”** locations
- Navigation panel features links to profile, favorite locations *(limit 3)*, recent reviews *(limit 3)*, links to transportation options *(lyft and uber on mobile view only)*
- Buttons to send alert text messages through twilio, both **“unsafe”** and **“safe”** options. 


## Profile Page:

Users can view and edit the following
- *Account information*
- *Reviews*
- *Favorite locations*
- *Personal location reviews*
- *Emergency contacts*


## Reviews Page:

- When a user clicks on a location on the main page map and selects **“View Reviews”** they are taken to this page
- If no reviews exist, users will be prompted to **“add the first review”**
- If reviews do exist, these will be displayed, along with the option to add new ones
