async function fetchProfileData() {
  const url = './data/profile.json';
  const response = await fetch(url);
  return await response.json();
}
