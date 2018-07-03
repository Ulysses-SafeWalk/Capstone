# SafeWalk Project

## FEATURES LIST:

## Home Page (logged out):

- Anyone can visit this page, whether they are logged in or not. Can see crime events without specific classification. 
- All features available to logged in users are listed to the left, along with buttons to either login or sign up.


## Login Page:

- Logged out users can log in
- If a user is already logged in and lands on this page, they will be redirected to the **“logged in”** home page.


## Register Page:

- Users without accounts can create a new user account. Will be directed to login page after account is created.
- If a user is already logged in and lands on this page, they will be redirected to the **“logged in”** home page.


## Home Page (logged in):

Users can interact with map in the following ways
- See information based on their current location
- Turn map layers on and off
- Crime events *(with color coordinated pins)*
- Parking information
- Public safety locations *(police/fire stations)*
- **“Heatmap”** layer based on custom algorithm
- Bars/restaurants
- **“Family friendly”** locations
- Navigation panel features links to profile, favorite locations *(limit 3)*, recent reviews *(limit 3)*, links to transportation options *(lyft and uber on mobile view only)*
- Buttons to send alert text messages through twilio, both **“unsafe”** and **“safe”** options. 


## Profile Page:

Users can view and edit the following
- *Account information*
- *Reviews*
- *Favorite locations*
- *Emergency contacts*


## Reviews Page:

- When a user clicks on a location on our main page map and select to **“View Reviews”** they are taken to this page
- If no reviews exist, users will be prompted to **“add the first review”**
- If reviews do exist, these will be displayed, along with the option to add new ones
