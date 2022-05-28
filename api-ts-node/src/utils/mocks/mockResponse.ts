import { Response } from 'express';

export type TMockResponse<TResult> = Response & {
  state: {
    status?: number;
    json?: TResult | unknown;
  };
};

export function makeMockResponse<TResult>() {
  const response = {
    state: {},
  } as TMockResponse<TResult>;

  response.status = (status: number) => {
    response.state.status = status;
    return response;
  };

  response.json = (json: TResult) => {
    response.state.json = json;
    return response;
  };

  return response;
}
