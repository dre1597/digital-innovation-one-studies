import { Controller } from '@hotwired/stimulus';

export default class extends Controller {
  connect() {
    this.render();
  }

  static base_uri = 'http://127.0.0.1:3333';

  async addStudent(event) {
    event.preventDefault();

    const name = this.element.querySelector('input[name=\'name\']').value;
    const phone = this.element.querySelector('input[name=\'phone\']').value;
    const code = this.element.querySelector('input[name=\'code\']').value;

    if (!name || name === '') {
      alert('Name is required');
      this.element.querySelector('input[name=\'name\']').focus();
      return;
    }

    if (!phone || phone === '') {
      alert('Phone is required');
      this.element.querySelector('input[name=\'phone\']').focus();
      return;
    }

    if (!code || code === '') {
      alert('Code is required');
      this.element.querySelector('input[name=\'code\']').focus();
      return;
    }

    const payload = {
      name,
      phone,
      code
    };

    const url = `${this.constructor.base_uri}/students`;

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        if (response.status === 422) {
          const errors = await response.json();
          alert(JSON.stringify(errors));
          return;
        }

        throw new Error(`HTTP Error: ${response.status}`);
      }

      this.render();

    } catch (error) {
      console.error('Error while creating student:', error);
    }

  }

  async updateStudent(event) {
    event.preventDefault();

    const id = this.element.querySelector('input[name=\'id\']').value;
    const name = this.element.querySelector('input[name=\'name\']').value;
    const phone = this.element.querySelector('input[name=\'phone\']').value;
    const code = this.element.querySelector('input[name=\'code\']').value;

    if (!id || id === '') {
      alert('Id is required');
      this.render();
      return;
    }

    if (!name || name === '') {
      alert('Name is required');
      this.element.querySelector('input[name=\'name\']').focus();
      return;
    }

    if (!phone || phone === '') {
      alert('Phone is required');
      this.element.querySelector('input[name=\'phone\']').focus();
      return;
    }

    if (!code || code === '') {
      alert('Code is required');
      this.element.querySelector('input[name=\'code\']').focus();
      return;
    }

    const payload = {
      name,
      phone,
      code
    };

    const url = `${this.constructor.base_uri}/students/${id}`;

    try {
      const response = await fetch(url, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        if (response.status === 422) {
          const errors = await response.json();
          alert(JSON.stringify(errors));
          return;
        }

        throw new Error(`HTTP Error: ${response.status}`);
      }

      this.render();

    } catch (error) {
      console.error('Error while updating student:', error);
    }

  }

  addNewForm() {
    this.element.innerHTML = `
      <form data-action="submit->students#addStudent">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" placeholder="Name">
        </div>
        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="tel" class="form-control" name="phone" placeholder="Phone">
        </div>
        <div class="form-group">
            <label for="code">Code</label>
            <input type="text" class="form-control" name="code" placeholder="Code">
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="button" data-action="click->students#render" class="btn btn-danger">Cancel</button>
      </form>
    `;
  }

  async render() {
    try {
      const response = await fetch(`${this.constructor.base_uri}/students`);
      if (!response.ok) {
        throw new Error(`Http Error: ${response.status}`);
      }
      const students = await response.json();

      console.log(students);

      if (students.length > 0) {
        this.element.innerHTML = `
          <button class="btn btn-primary" data-action="click->students#addNewForm">New</button>
          <hr>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Phone</th>
                <th scope="col">Code</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              ${students.map(student => `
                  <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.phone}</td>
                    <td>${student.code}</td>
                    <td style="width: 200px">
                      <button type="button" data-action="click->students#edit" data-student-id="${student.id}" class="btn btn-warning">Edit</button>
                      <button type="button" class="btn btn-danger" data-action="click->students#deleteStudent" data-student-id=${student.id} >Delete</button>
                    </td>
                  </tr>`
        ).join('')}
            </tbody>
          </table>
        `;
      } else {
        this.element.innerHTML = `
          <button class="btn btn-primary" data-action="click->students#addNewForm">New</button>
          <h3>No students...</h3>
        `;
      }

    } catch (error) {
      console.error('Error while fetching students:', error);
    }
  }

  async edit(event) {
    try {
      this.element.innerHTML = 'Loading ...';
      const id = event.currentTarget.dataset.studentId;

      const url = `${this.constructor.base_uri}/students/${id}`;
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        }
      });

      if (!response.ok) {
        throw new Error(`Erro HTTP: ${response.status}`);
      }

      const student = await response.json();

      this.element.innerHTML = `
        <form data-action="submit->students#updateStudent">
          <div class="form-group">
              <label for="name">Name</label>
              <input type="hidden" name="id" value="${student.id}">
              <input type="text" class="form-control" name="name" value="${student.name}" placeholder="Name">
          </div>
          <div class="form-group">
              <label for="phone">Phone</label>
              <input type="tel" class="form-control" name="phone" value="${student.phone}" placeholder="Phone">
          </div>
          <div class="form-group">
              <label for="code">Code</label>
              <input type="text" class="form-control" name="code" value="${student.code}" placeholder="Code">
          </div>
          <br>
          <button type="submit" class="btn btn-primary">Submit</button>
          <button type="button" data-action="click->students#render" class="btn btn-danger">Cancel</button>
        </form>
      `;

    } catch (error) {
      console.error('Error while fetching student:', error);
    }
  }

  async deleteStudent(event) {
    console.log(event.currentTarget.dataset);
    const id = event.currentTarget.dataset.studentId;
    if (confirm('Confirm delete?')) {
      try {
        const url = `${this.constructor.base_uri}/students/${id}`;
        const response = await fetch(url, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
          }
        });

        if (!response.ok) {
          if (response.status === 422) {
            const error = await response.json();
            alert(JSON.stringify(error));
            return;
          }

          throw new Error(`Http Error: ${response.status}`);
        }

        this.render();

      } catch (error) {
        console.error('Error while deleting student:', error);
      }
    }
  }
}
