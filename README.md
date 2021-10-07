# discovery_miles (40 / 32 = 80%)
# Software (25 / 22)
## Main goals 
- [x] Add miles to account 
- [x] View miles 
- [x] Use miles to buy Rewards

##Must haves
- [x] Demonstrate build tool
- [x] Has a database with sufficient tables
- [x] Can connect and interact with database
- [x] In which way implemented unit tests 
  - Sort of done domain has working tests
- [x] Can run as a standalone application using spring boot
- [x] Source control implemented

##Should haves
- [x] Can specify start date on service that adds miles
    - Done by having a start date that can be added to the weekly goal
- [x] Proper error handing
- [x] Proper application layering
- [x] Swagger API implemented 
   - Does work but not relevant considering I use normal controller to call services instead of rest controller
- [ ] Code coverage report (-1)
   - explain
- [x] Has an optional field on add service that will cause service to throw an exception 
- [x] Demonstrates roll-back occurs if an exception occurs 
- [x] Logging was included

##Could haves 
- [ ] Can run in docker (-0.5)
  - tried didn't work
- [x] Can handle more than one currency
- [ ] 80% unit tests in code coverage (-0.5)
  - tried mockito didn't want to work

##Will not haves
- [x] Front end that calls services


#Documentation (15 / 10)
- [x] Appropriate cover page
   - No clue if using the right template
- [x] Table of contents
- [x] List of figures or table, use a template that auto creates it
- [x] Brief introduction to project and general system overview
- [x] ERD diagram 
- [ ] USE case diagram (-2)
- [x] Flow diagram for every service
   - Sort of just needs tweaking
- [ ] User how to guide (-2)
- [ ] Code coverage report (-1)
- [x] Professional document 

## Sub goals 
### Database
- [ ] Generate voucher code 

### Classes 
- [ ] Figure out medical information 

