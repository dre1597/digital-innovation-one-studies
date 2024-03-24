const conta: IAccount = {
  email: 'any_email@email.com',
  password: '123456',
  name: 'any_name',
  balance: 2000.00,
  id: '1'
};

interface IAccount {
  email: string;
  password: string;
  name: string;
  balance: number;
  id: string;
}

export const api: Promise<IAccount> = new Promise((resolve) => {
  setTimeout(() => {
    resolve(conta);
  }, 3000);
});
