export class Reviewer {
  name: string;
  expertise: Array<string>;
  id: number;

  constructor(name: string, expertise: Array<string>) {
    this.name = name;
    this.expertise = expertise;
  }
}
