export abstract class DioAccount {
  private readonly name: string;
  private readonly accountNumber: number;
  private balance: number;
  private status: boolean = true;

  protected constructor(name: string, accountNumber: number) {
    this.name = name;
    this.accountNumber = accountNumber;
    this.balance = 0;
  }

  public getBalance(): number {
    return this.balance;
  }

  public deposit(value: number): void {
    if (!this.validateStatus()) {
      throw new Error('Your account is closed');
    }

    if (value < 0) {
      throw new Error('Value must be positive');
    }

    this.balance += value;
  }

  public withdraw(value: number): void {
    if (!this.validateStatus()) {
      throw new Error('Your account is closed');
    }

    if (value < 0) {
      throw new Error('Value must be positive');
    }

    if (this.balance < value) {
      throw new Error('Insufficient balance');
    }

    this.balance -= value;
  }

  private validateStatus(): boolean {
    if (!this.status) {
      throw new Error('Your account is closed');
    }

    return this.status;
  }
}
