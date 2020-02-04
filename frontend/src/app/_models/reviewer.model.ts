export class Reviewer {
  name: string;
  expertise: Array<string>;

  constructor(name: string, expertise: Array<string>) {
    this.name = name;
    this.expertise = expertise;
  }
}
