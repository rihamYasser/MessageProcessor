# MessageProcessor
A small message processing maven Java8 application for processing sales notification messages.

Processing logic:
----------------
- All sales must be recorded
- All messages must be processed
- After every 10th message received your application should log a report detailing the number of sales of each product
  and their total value.
 - After 50 messages your application should log that it is pausing, stop accepting new messages and log a report of the adjustments that have been made to each sale type while the application was running.

Sales and Messages:
-------------------
 - A sale has a product type field and a value.
 - Any number of different product types can be expected. There is no fixed set.
 - A message notifying you of a sale could be one of the following types
    - SINGLE_SALE_MESSAGE – contains the details of 1 sale E.g apple at 10p
    - MULTIPLE_SALES_MESSAGE – contains the details of a sale and the number of occurrences of that sale. E.g 20 sales of apples at 10p each.
    - ADJUSTMENT_MESSAGE – contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. Operations can be add, subtract, or multiply e.g Add 20p apples would instruct your application to add 20p to each sale of apples you have recorded.


How To run Application:
-----------------------
 Run MessageSender class in either one of the following ways:
    1. Without providing any arguments and it will send all json messages under resources/sample_messages directory. You
    can add new messages or change the existing messages.
    2.Provide these arguments: bulk bulk_message.json, where "bulk_message.json" is name of the file under resources
    directory that contain list of messages in json format.

Future Enhancements:
--------------------
  - Add PRODUCT_SALE_REPORT_THRESHOLD and SALE_ADJUSTMENT_REPORT_THRESHOLD to configuration.properties file, so that it
   can be easily configured.
  - All all messages/errors in messages.properties/errors.properties file so it can be easily configured.
  - Add more unit testing.
  - Use Spring framework or any Dependency container for better bean injection management.
  - Create POJO for each Message Type.
  - Move printReport() method from MessageProcessor to anywhere else, printing should npt be part of message processing.
  - Implement proper logging and proper Exception handling.
  - Use good reporting tool like jasper report instead of printing on console.
  - Implement Observer pattern or message-subscribe pattern for loose coupling between MessageSender and
     MessageReceiver.
  - Proper handling for application pause after SALE_ADJUSTMENT_REPORT_THRESHOLD mesages, currently the application
  just sleeps for 2 seconds then continue processing.

