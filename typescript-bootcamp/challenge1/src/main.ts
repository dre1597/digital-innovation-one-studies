import { PeopleAccount } from './PeopleAccount';

const peopleAccount: PeopleAccount = new PeopleAccount(1, 'any_person', 10);
peopleAccount.deposit();
const companyAccount: PeopleAccount = new PeopleAccount(2, 'any_company', 20);
companyAccount.deposit();
