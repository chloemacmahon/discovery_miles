# discovery_miles (40 / 22 = 55%)
# Software (25 / 17)
## Main goals 
- [x] Add miles to account 
- [x] View miles 
- [ ] Use miles to buy Rewards

##Must haves
- [x] Demonstrate build tool
- [x] Has a database with sufficient tables
- [x] Can connect and interact with database
- [ ] In which way implemented unit tests (-1.5)
  - Sort of done domain has working tests
- [x] Can run as a standalone application using spring boot
- [x] Source control implemented

##Should haves
- [x] Can specify start date on service that adds miles
    - Done by having a start date that can be added to the weekly goal
- [x] Proper error handing
- [x] Proper application layering
- [ ] Swagger API implemented (-1)
   - Does work but not relevant considering I use normal controller to call services instead of rest controller
- [ ] Code coverage report (-1)
   - explain
- [ ] Has an optional field on add service that will cause service to throw an exception (-1)
- [ ] Demonstrates roll-back occurs if an exception occurs (-1)
- [ ] Logging was included (-1)

##Could haves 
- [ ] Can run in docker (-0.5)
- [ ] Can handle more than one currency (-0.5)
    - Can handle points, miles and rands, remember to configure translate currency service
- [ ] 80% unit tests in code coverage (-0.5)

##Will not haves
- [x] Front end that calls services


#Documentation (15 / 5)
- [x] Appropriate cover page
   - No clue if using the right template
- [x] Table of contents
- [ ] List of figures or table, use a template that auto creates it (-1)
- [ ] Brief introduction to project and general system overview (-2)
- [ ] ERD diagram (-2)
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

