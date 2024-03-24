import { PeopleAccount } from './PeopleAccount';
import { CompanyAccount } from './CompanyAccount';
import { BonusAccount } from './BonusAccount';

const peopleAccount: PeopleAccount = new PeopleAccount(1, 'any_person', 10);
peopleAccount.deposit(10);
peopleAccount.withdraw(5);
console.log(peopleAccount.getBalance());

const companyAccount: CompanyAccount = new CompanyAccount('any_company', 10);
companyAccount.deposit(1000);
companyAccount.withdraw(100);
companyAccount.getLoan(10, peopleAccount);
console.log(companyAccount.getBalance())

const bonusAccount: BonusAccount = new BonusAccount('any_company', 10);
bonusAccount.deposit(10);
bonusAccount.withdraw(5);
console.log(bonusAccount.getBalance())
