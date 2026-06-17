1. Carrier deposits a package by specifying size - small med large
    - System assigns an available compartment of matching size
    - Opens compartment and returns access token, or error if no space
2. Upon successful deposit, an access token is generated and returned
    - one access token per package
3. User retrieves package by entering access token
    -System validates code and opens compartment
    -Throws specific error if code is invalid or expired
4. Access tokens expire after 7 days
    -expired codes are rejected if used for pickup
    -Package remains in compartment until staff removes it
5. Staff can open all compartments to manually handle packages
    -System opens all compartments with expired token
    -Staff physically removes packages and returns them to sender
6. Invalid access tokens are rejected with clear error messages