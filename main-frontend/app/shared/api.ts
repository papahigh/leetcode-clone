import axios from 'redaxios';

import type { CreateSubmission, SubmissionDetails, ProblemDetails, ProblemSummary } from './types';

class ApiClient {
  constructor(private axiosClient: typeof axios) {}

  public async getAllProblems(): Promise<ProblemSummary[]> {
    return (await this.axiosClient.get('/api/problems')).data;
  }

  public async getProblemBySlug(slug: string): Promise<ProblemDetails> {
    return (await this.axiosClient.get(`/api/problems/${slug}`)).data;
  }

  public async createSubmission(request: CreateSubmission): Promise<SubmissionDetails> {
    return (await this.axiosClient.post('/api/submissions', request)).data;
  }

  public async getSubmissionById(id: string): Promise<SubmissionDetails> {
    return (await this.axiosClient.get(`/api/submissions/${id}`)).data;
  }
}

export const api = new ApiClient(
  axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    responseType: 'json',
    withCredentials: false,
  }),
);
