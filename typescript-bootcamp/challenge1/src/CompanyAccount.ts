import { DioAccount } from './DioAccount';

export class CompanyAccount extends DioAccount {
  constructor(name: string, accountNumber: number) {
    super(name, accountNumber);
  }

  public getLoan(value: number, account: DioAccount): void {
    this.withdraw(value);
    account.deposit(value);
  }
}
