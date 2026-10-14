Feature: Verify booking api from https://restful-booker.herokuapp.com/

  Scenario: Create booking
    Given "booking" payload
    When user calls "createBooking" api request
    Then verify api status code 200
    Then verify "bookingid" is created

  Scenario: Create token
    Given "token" payload
    When user calls "createToken" api request
    Then verify api status code 200
    Then verify "token" is created

  Scenario Outline: Update booking
    Given "update" payload with "<fName>" "<lName>" and "<additionalNeeds>"
    When user calls "updateBooking" api request
    Then verify api status code 200
    Then verify "<fName>" "<lName>" and "<additionalNeeds>" is created using getBooking api

  Examples:
    | fName  | lName   | additionalNeeds |
    | John   | Terry   |    Brunch       |

  Scenario Outline: Update partial booking
    Given "partialUpdate" payload with "<fName>" "<lName>" and "<additionalNeeds>"
    When user calls "partialUpdateBooking" api request
    Then verify api status code 200
    Then verify "<fName>" "<lName>" and "<additionalNeeds>" is created using getBooking api

    Examples:
      | fName  | lName     | additionalNeeds |
      | Kiron  | Pollard   |    Dinner       |

  Scenario: Delete booking
    Given "delete" payload
    When user calls "deleteBooking" api request
    Then verify api status code 201

  