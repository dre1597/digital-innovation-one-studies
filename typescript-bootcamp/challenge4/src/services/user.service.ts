export interface User {
  name: string
  email: string
}

const db = [
  {
    name: "Joe",
    email: "joe@email.com",
  }
]

export class UserService {
  db: User[]
  createUser = (name: string, email: string) => {
    const user = {
      name,
      email
    }

    this.db.push(user)
    console.log('DB updated', this.db)
  }
  getAllUsers = () => {
    return this.db
  }

  constructor(
    database = db
  ){
    this.db = database
  }
}

