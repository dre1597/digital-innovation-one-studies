interface Car {
  name: string;
  plate: string;
  entranceTime: Date | string;
}

(function () {
  const $ = (query: string): HTMLInputElement | null =>
    document.querySelector(query);

  function calcTime(mil: number) {
    const min = Math.floor(mil / 60000);
    const sec = Math.floor((mil % 60000) / 1000);

    return `${min}min and ${sec}s`;
  }

  function park() {
    function read(): Car[] {
      return localStorage.park ? JSON.parse(localStorage.park) : [];
    }
    function add(car: Car, haveToSave?: boolean) {
      const row = document.createElement('tr');

      row.innerHTML = `
        <td>${car.name}</td>
        <td>${car.plate}</td>
        <td>${car.entranceTime}</td>
        <td>
          <button class='delete' data-plate='${car.plate}'>x</button>
        </td>
      `;

      row.querySelector('.delete')?.addEventListener('click', function () {
        remove(this.dataset.plate);
      });

      $('#park')?.appendChild(row);

      if (haveToSave) save([...read(), car]);
    }
    function remove(plate: string) {
      const { entranceTime, name } = read().find((car) => car.plate === plate);

      const time = calcTime(
        new Date().getTime() - new Date(entranceTime).getTime()
      );

      if (!confirm(`The car ${name} stayed by: ${time}. Want to leave?`))
        return;

      save(read().filter((car) => car.plate !== plate));
      render();
    }
    function save(cars: Car[]) {
      localStorage.setItem('park', JSON.stringify(cars));
    }
    function render() {
      $('#park')!.innerHTML = '';
      const park = read();

      if (park.length) {
        park.forEach((car) => add(car));
      }
    }

    return { read, add, remove, save, render };
  }

  park().render();
  $('#submit')?.addEventListener('click', () => {
    const name = $('#name')?.value;
    const plate = $('#plate')?.value;

    if (!name || !plate) {
      alert('Required fields!');
      return;
    }

    park().add({ name, plate, entranceTime: new Date().toISOString() }, true);
  });
})();
