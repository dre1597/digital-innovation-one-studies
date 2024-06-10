import textwrap
from abc import ABC, abstractproperty, abstractclassmethod
from datetime import datetime


class Customer:
    def __init__(self, address):
        self.address = address
        self.accounts = []

    @staticmethod
    def make_transaction(account, transaction):
        transaction.register(account)

    def add_account(self, account):
        self.accounts.append(account)


class Person(Customer):
    def __init__(self, name, birthdate, cpf, address):
        super().__init__(address)
        self.name = name
        self.birthdate = birthdate
        self.cpf = cpf


class Historical:
    def __init__(self):
        self._transactions = []

    @property
    def transactions(self):
        return self._transactions

    def add_transaction(self, transaction):
        self._transactions.append(
            {
                "type": transaction.__class__.__name__,
                "value": transaction.value,
                "date": datetime.now().strftime("%d/%m/%Y %H:%M:%S"),
            }
        )


class Account:
    def __init__(self, number, customer):
        self._balance = 0
        self._number = number
        self._agency = "0001"
        self._customer = customer
        self._historical = Historical()

    @classmethod
    def new_account(cls, number, customer):
        return cls(number, customer)

    @property
    def balance(self):
        return self._balance

    @property
    def number(self):
        return self._number

    @property
    def agency(self):
        return self._agency

    @property
    def customer(self):
        return self._customer

    @property
    def historical(self):
        return self._historical

    def withdraw(self, value):
        balance = self.balance
        has_balance = balance >= value > 0

        if has_balance:
            self._balance -= value
            return True
        return False

    def deposit(self, value):
        if value > 0:
            self._balance += value
            return True
        return False


class CheckingAccount(Account):
    def __init__(self, number, customer, limit=500, deposit_limit=3):
        super().__init__(number, customer)
        self._limit = limit
        self._deposit_limit = deposit_limit

    def deposit(self, value):
        deposit_count = len(
            [transaction for transaction in self.historical.transactions if transaction["type"] == Deposit.__name__]
        )

        exceeded_limit = value > self._limit
        exceeded_deposit = deposit_count >= self._deposit_limit

        if exceeded_limit:
            print("\n@@@ Operation failed! The amount exceeds the limit . @@@")

        elif exceeded_deposit:
            print("\n@@@ Operation failed! The amount exceeds the limit. @@@")

        else:
            return super().withdraw(value)

        return False

    def __str__(self):
        return f"""\
            Agency:\t{self.agency}
            C/C:\t\t{self.number}
            Customer:\t{self.customer.name}
        """


class Transaction(ABC):
    @property
    @abstractproperty
    def value(self):
        pass

    @abstractclassmethod
    def register(cls, account):
        pass


class Withdraw(Transaction):
    def __init__(self, valor):
        self._valor = valor

    @property
    def value(self):
        return self._valor

    def register(self, account):
        success = account.withdraw(self.value)

        if success:
            account.historical.add_transaction(self)


class Deposit(Transaction):
    def __init__(self, value):
        self._value = value

    @property
    def value(self):
        return self._value

    def register(self, account):
        success = account.deposit(self.value)

        if success:
            account.historical.add_transaction(self)


def menu():
    text_menu = """\n
    ================ MENU ================
    [d]\tDeposit
    [w]\tWithdraw
    [e]\tExtract
    [na]\tNew account
    [la]\tList accounts
    [nu]\tNew user
    [lu]\tList users
    [q]\tQuit
    => """
    return input(textwrap.dedent(text_menu))


def filter_customers(cpf, customers):
    filtered_customers = [customer for customer in customers if customer.cpf == cpf]
    return filtered_customers[0] if filtered_customers else None


def recover_account(customer):
    if not customer.accounts:
        print("\n@@@ Customer has no accounts! @@@")
        return

    return customer.accounts[0]


def deposit(customers):
    cpf = input("CPF: ")
    customer = filter_customers(cpf, customers)

    if not customer:
        print("\n@@@ Customer not found! @@@")
        return

    value = float(input("Value: "))
    transaction = Deposit(value)

    account = recover_account(customer)
    if not account:
        return

    customer.make_transaction(account, transaction)


def withdraw(clientes):
    cpf = input("CPF: ")
    customer = filter_customers(cpf, clientes)

    if not customer:
        print("\n@@@ Customer not found! @@@")
        return

    value = float(input("Value: "))
    transaction = Withdraw(value)

    account = recover_account(customer)
    if not account:
        return

    customer.make_transaction(account, transaction)


def show_extract(clientes):
    cpf = input("CPF: ")
    customer = filter_customers(cpf, clientes)

    if not customer:
        print("\n@@@ Customer not found! @@@")
        return

    account = recover_account(customer)
    if not account:
        return

    print("\n================ EXTRATO ================")
    transactions = account.historical.transactions

    extract = ""
    if not transactions:
        extract = "Its empty ."
    else:
        for transaction in transactions:
            extract += f"\n{transaction['type']}:\n\tR$ {transaction['value']:.2f}"

    print(extract)
    print(f"\nBalance:\n\tR$ {account.balance:.2f}")
    print("==========================================")


def add_customer(customers):
    cpf = input("CPF: ")
    customer = filter_customers(cpf, customers)

    if customer:
        print("\n@@@ CPF already registered  ! @@@")
        return

    name = input("Name: ")
    birthdate = input("Birthdate (dd-mm-aaaa): ")
    address = input("Address: ")

    customer = Person(name=name, birthdate=birthdate, cpf=cpf, address=address)

    customers.append(customer)

    print("\n=== Customer created with success!  ! ===")


def list_customers(customers):
    for customer in customers:
        print("\n=========================================")
        print({
            "name": customer.name,
            "birthdate": customer.birthdate,
            "cpf": customer.cpf,
            "address": customer.address
        })


def add_account(number, customers, accounts):
    cpf = input("CPF: ")
    customer = filter_customers(cpf, customers)

    if not customer:
        print("\n@@@ Customer not found! @@@")
        return

    account = CheckingAccount.new_account(number=number, customer=customer)
    accounts.append(account)
    customer.add_account(account)

    print("\n=== Account created with success! ===")


def list_accounts(contas):
    for conta in contas:
        print("=" * 100)
        print(textwrap.dedent(str(conta)))


def main():
    customers = []
    accounts = []

    while True:
        option = menu()

        if option == "d":
            deposit(customers)

        elif option == "w":
            withdraw(customers)

        elif option == "e":
            show_extract(customers)

        elif option == "nu":
            add_customer(customers)

        elif option == "lu":
            list_customers(customers)

        elif option == "na":
            number = len(accounts) + 1
            add_account(number, customers, accounts)

        elif option == "la":
            list_accounts(accounts)

        elif option == "q":
            break

        else:
            print("\n@@@ Invalid Option @@@")


main()
