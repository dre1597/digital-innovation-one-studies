//Get your key here: https://pro.coinmarketcap.com/account
const apiKey = {
    key: 'PUT YOUR KEY HERE',
};
fetch(
    'https://pro-api.coinmarketcap.com/v1/cryptocurrency/map?CMC_PRO_API_KEY=' +
        apiKey.key
)
    .then((res) => {
        if (!res.ok)
            throw new Error(
                'Erro ao executar a requsição, status ' + res.status
            );
        return res.json();
    })
    .then((api) => {
        let text = '';
        for (let i = 0; i < 10; i++) {
            let date = new Date(api.data[i].first_historical_data);
            let dateFormated = `${date.getFullYear()}-${
                date.getMonth() + 1
            }-${date.getDate()}`;
            text += `
        <div class="media">
            <img src="coin.jpg" class="align-self-center me-3" width="100" height="60">
            <div class="media-body">
                <h5 class="mt-2">${api.data[i].name}</h5>
                <p>${api.data[i].symbol}</p>
                <p>First historical data: ${dateFormated}</p>
            </div>
        </div>
        `;
            // <p>${api.data[i].first_historical_data}</p>
            document.getElementById('coins').innerHTML = text;
        }
    })
    .catch((error) => {
        console.error(error.message);
    });
