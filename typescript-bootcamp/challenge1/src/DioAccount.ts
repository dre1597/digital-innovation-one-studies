export abstract class DioAccount {
  public balance: number;
  private name: string;
  private readonly accountNumber: number;
  private status: boolean = true;

  constructor(name: string, accountNumber: number) {
    this.name = name;
    this.accountNumber = accountNumber;
    this.balance = 0;
  }

  public setName(name: string) {
    this.name = name;
  }

  public getName(): string {
    return this.name;
  }

  public getBalance(): number {
    return this.balance;
  }

  public deposit() {
    if(this.validateStatus()) {
      console.log("Depositing...");
    }
  }

  public withdraw() {
    if(this.validateStatus()) {
      console.log("Withdrawing...");
    }
  }

  private validateStatus(): boolean {
    if (!this.status) {
      throw new Error("Your account is closed");
    }

    return this.status;
  }
}
