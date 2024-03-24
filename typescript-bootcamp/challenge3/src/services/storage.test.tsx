import { changeLocalStorage, createLocalStorage, getAllLocalStorage } from './storage';

const dioBank = {
  login: false
};

describe('storage', () => {
  const mockSetItem = jest.spyOn(Storage.prototype, 'setItem');
  it('should return the object on local storage', () => {
    const mockGetItem = jest.spyOn(Storage.prototype, 'getItem');
    getAllLocalStorage();
    expect(mockGetItem).toHaveBeenCalledWith('diobank');
  });

  it('should create the object on local storage', () => {
    createLocalStorage();
    expect(mockSetItem).toHaveBeenCalledWith('diobank', JSON.stringify(dioBank));
  });

  it('should change the object on local storage', () => {
    changeLocalStorage(dioBank);
    expect(mockSetItem).toHaveBeenCalledWith('diobank', JSON.stringify(dioBank));
  });
});
